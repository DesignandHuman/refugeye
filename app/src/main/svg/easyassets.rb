#!/usr/bin/env ruby

# SVG to PNG easy image asset converter
# Copyright (c) 2013, YouEye, Inc. www.youeye.com <support@youeye.com>
# Developed by Borys Boyko, Application Engineer
# All rights reserved.
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

# Usage: ./easyassets.rb [path/to/image].svg [sw320dp-width (optional)] [sw320dp-height (optional)]
#
# Suggested workflow:
# 1. Create an SVG file with a canvas of size 320 x 480 pixels
# 2. Draw your vectors on this canvas
# 3. Save the image as image_name.svg
# 4. Run the script, giving 320 and 480 as the last two arguments (or leave them both blank)
# 5. The images will be generated in the directory that the script ran from

# Instructions for OSX: first, install librsvg, pngquant and imagemagick using homebrew:
#                   brew install librsvg
#                   brew install pngquant
#                   brew install imagemagick --with-librsvg
# If you do not have homebrew, please visit <http://brew.sh/>
#
# Instructions for Ubuntu: first, install librsvg, pngquant and imagemagick using apt:
#                   sudo apt-get update
#                   sudo apt-get install librsvg2-bin
#                   sudo apt-get install pngquant
#                   sudo apt-get install imagemagick --fix-missing
#
# If you do not have ruby, please visit <https://www.ruby-lang.org/en/downloads/>
#                                    or <http://railsinstaller.org/en>

if __FILE__==$0
    if (ARGV.length < 1 || (!(Integer(ARGV[ARGV.length - 2]) rescue false) && (Integer(ARGV[ARGV.length - 1]) rescue false)))
        puts "\nUsage: #{$0} [path/to/image].svg [sw320dp-width (optional)] [sw320dp-height (optional)]\n\n"
        puts "Suggested workflow:"
        puts "1. Create an SVG file with a canvas of size 320 by 480 pixels"
        puts "2. Draw your vectors on this canvas"
        puts "3. Save the image as image_name.svg"
        puts "4. Run the script, giving 320 and 480 as the last two arguments (or leave them both blank)"
        puts "5. The images will be generated in the directory that the script ran from\n\n"
        else
        # Android:
        # ldpi:mdpi:hdpi:xhdpi:xxhdpi ratios are 3:4:6:8:12
        # sw320dp:sw360dp:sw480dp:sw600dp:sw720dp ratios are 8:9:12:15:18
        #
        # Ratio map:
        #               320         360         480         600         720
        #             ---------------------------------------------------------
        #      ldpi   | 0.75        0.84375     1.125       1.40625     1.6875
        #      mdpi   | 1           1.125       1.5         1.875       2.25
        #      hdpi   | 1.33        1.49625     1.995       2.49375     2.9925
        #     xhdpi   | 2           2.25        3           3.75        4.5
        #    xxhdpi   | 3           3.375       4.5         5.625       6.75
        
        # iOS:
        # iphone1:iphone4:iphone5:ipad:ipad3 ratios are 5:10:10:16:32
        # mdpi:iphone1 ratio is 1:1
        
        scaleRatios = {"drawable-ldpi" => 0.75,
            "drawable-mdpi" => 1.0,
            "drawable-hdpi" => 1.5,
            "drawable-xhdpi" => 2.0,
            "drawable-xxhdpi" => 3.0,
            "drawable-sw360dp-ldpi" => 0.84375,
            "drawable-sw360dp-mdpi" => 1.125,
            "drawable-sw360dp-hdpi" => 1.6875,
            "drawable-sw360dp-xhdpi" => 2.25,
            "drawable-sw360dp-xxhdpi" => 3.375,
            "drawable-sw480dp-ldpi" => 1.125,
            "drawable-sw480dp-mdpi" => 1.5,
            "drawable-sw480dp-hdpi" => 2.25,
            "drawable-sw480dp-xhdpi" => 3.0,
            "drawable-sw480dp-xxhdpi" => 4.5,
            "drawable-sw600dp-ldpi" => 1.40625,
            "drawable-sw600dp-mdpi" => 1.875,
            "drawable-sw600dp-hdpi" => 2.8125,
            "drawable-sw600dp-xhdpi" => 3.75,
            "drawable-sw600dp-xxhdpi" => 5.625,
            "drawable-sw720dp-ldpi" => 1.6875,
            "drawable-sw720dp-mdpi" => 2.25,
            "drawable-sw720dp-hdpi" => 3.375,
            "drawable-sw720dp-xhdpi" => 4.5,
            "drawable-sw720dp-xxhdpi" => 6.75,
            "iphone1_3g_3gs" => 1.0,
            "iphone4_4s_5_5s" => 2.0,
            "ipad1_2_mini" => 3.2,
            "ipad3_4_air" => 6.4}
        
        arguments = ARGV
        arguments.drop(1)
        
        originalWidth = 0
        originalHeight = 0
        
        if ((Integer(ARGV[ARGV.length - 2]) rescue false) && (Integer(ARGV[ARGV.length - 1]) rescue false))
            widthAndHeight = arguments.pop(2)
            originalWidth = widthAndHeight[0].to_f
            originalHeight = widthAndHeight[1].to_f
        end
        
        arguments.each do |originalFileNames|
            Dir[originalFileNames].each do |originalFileName|
                outputFileName = File.basename(originalFileName, ".*") + ".png"
                puts "Converting %s to %s\n\n" % [originalFileName, outputFileName]
                
                if (originalWidth == 0 || originalHeight == 0)
                    command = "identify #{originalFileName}"
                    puts "Run: #{command}\n\n"
                    identifyOutput = `#{command}`
                    identifyOutput = identifyOutput.split[3].split("x")
                    originalWidth = identifyOutput[0].to_f
                    originalHeight = identifyOutput[1].to_f
                end
                
                puts "Scaling from width %s and height %s\n\n" % [originalWidth, originalHeight]
                
                scaleRatios.each do |dir, ratio|
                    outputWidth = originalWidth * ratio
                    outputHeight = originalHeight * ratio
                    outputWidth = outputWidth.to_i
                    outputHeight = outputHeight.to_i
                    outputWidthHeight = "#{outputWidth}x#{outputHeight}\\!"
                    output = "./#{dir}/#{outputFileName}"
                    
                    # Make appropriate directory, if necessary
                    unless File.exists?(dir)
                        puts "Making directory: ./#{dir}/\n"
                        Dir.mkdir(dir)
                    end
                    
                    # Convert svg to png
                    command = "convert -resize #{outputWidthHeight} -density 400 -depth 8 -strip -background none #{originalFileName} #{output}"
                    puts "Run: #{command}\n"
                    `#{command}`
                    
                    # Trim png whitespace
                    command = "convert -trim #{output} #{output}"
                    puts "Run: #{command}\n"
                    `#{command}`
                    
                    # Make filesize much smaller
                    command = "pngquant -f #{output} -o #{output}"
                    puts "Run: #{command}\n\n"
                    `#{command}`
                end
            end
        end
    end
end